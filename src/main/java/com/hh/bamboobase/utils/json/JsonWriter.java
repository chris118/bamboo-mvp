package com.hh.bamboobase.utils.json;

import android.text.TextUtils;

import java.util.ArrayList;

public final class JsonWriter
{
    private final StringBuilder sb;

    private final ArrayList<JsonScope> stack = new ArrayList<JsonScope>();
    {
        stack.add(JsonScope.EMPTY_DOCUMENT);
    }

    private String indent;

    private String separator = ":";

    private boolean lenient;

    public JsonWriter()
    {
        sb = new StringBuilder(128);
    }

    public void setIndent(String indent)
    {
        if (TextUtils.isEmpty(indent))
        {
            this.indent = null;
            this.separator = ":";
        }
        else
        {
            this.indent = indent;
            this.separator = ": ";
        }
    }

    public void setLenient(boolean lenient)
    {
        this.lenient = lenient;
    }

    public boolean isLenient()
    {
        return lenient;
    }

    public JsonWriter beginArray()
    {
        return open(JsonScope.EMPTY_ARRAY, "[");
    }

    /**
     * Ends encoding the current array.
     * 
     * @return this writer.
     */
    public JsonWriter endArray()
    {
        return close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
    }

    /**
     * Begins encoding a new object. Each call to this method must be paired
     * with a call to {@link #endObject}.
     * 
     * @return this writer.
     */
    public JsonWriter beginObject()
    {
        return open(JsonScope.EMPTY_OBJECT, "{");
    }

    public JsonWriter endObject()
    {
        return close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
    }

    private JsonWriter open(JsonScope empty, String openBracket)
    {
        beforeValue(true);
        stack.add(empty);
        sb.append(openBracket);
        return this;
    }

    private JsonWriter close(JsonScope empty, JsonScope nonempty, String closeBracket)
    {
        JsonScope context = peek();
        if (context != nonempty && context != empty)
        {
            throw new IllegalStateException("Nesting problem: " + stack);
        }

        stack.remove(stack.size() - 1);
        if (context == nonempty)
        {
            newline();
        }
        sb.append(closeBracket);
        return this;
    }

    private JsonScope peek()
    {
        return stack.get(stack.size() - 1);
    }

    private void replaceTop(JsonScope topOfStack)
    {
        stack.set(stack.size() - 1, topOfStack);
    }

    public JsonWriter name(String name)
    {
        if (name == null)
        {
            throw new NullPointerException("name == null");
        }
        beforeName();
        string(name);
        return this;
    }

    public JsonWriter value(String value)
    {
        if (value == null)
        {
            return nullValue();
        }
        beforeValue(false);
        string(value);
        return this;
    }

    public JsonWriter nullValue()
    {
        beforeValue(false);
        sb.append("null");
        return this;
    }

    public JsonWriter value(boolean value)
    {
        beforeValue(false);
        sb.append(value ? "true" : "false");
        return this;
    }

    public JsonWriter value(double value)
    {
        if (!lenient && (Double.isNaN(value) || Double.isInfinite(value)))
        {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        beforeValue(false);
        sb.append(Double.toString(value));
        return this;
    }

    public JsonWriter value(long value)
    {
        beforeValue(false);
        sb.append(Long.toString(value));
        return this;
    }

    public JsonWriter value(Number value)
    {
        if (value == null)
        {
            return nullValue();
        }

        String string = value.toString();
        if (!lenient && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN")))
        {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        beforeValue(false);
        sb.append(string);
        return this;
    }

    private void string(String value)
    {
        sb.append("\"");
        for (int i = 0, length = value.length(); i < length; i++)
        {
            char c = value.charAt(i);

            switch (c)
            {
            case '"':
            case '\\':
                sb.append('\\');
                sb.append(c);
                break;

            case '\t':
                sb.append("\\t");
                break;

            case '\b':
                sb.append("\\b");
                break;

            case '\n':
                sb.append("\\n");
                break;

            case '\r':
                sb.append("\\r");
                break;

            case '\f':
                sb.append("\\f");
                break;

            case '\u2028':
            case '\u2029':
                sb.append(String.format("\\u%04x", Integer.valueOf(c)));
                break;

            default:
                if (c <= 0x1F)
                {
                    sb.append(String.format("\\u%04x", Integer.valueOf(c)));
                }
                else
                {
                    sb.append(c);
                }
                break;
            }

        }
        sb.append("\"");
    }

    private void newline()
    {
        if (indent == null)
        {
            return;
        }

        sb.append("\n");
        for (int i = 1; i < stack.size(); i++)
        {
            sb.append(indent);
        }
    }

    private void beforeName()
    {
        JsonScope context = peek();
        if (context == JsonScope.NONEMPTY_OBJECT)
        {
            // first in object
            sb.append(',');
        }
        else if (context != JsonScope.EMPTY_OBJECT)
        {
            // not in an object!
            throw new IllegalStateException("Nesting problem: " + stack);
        }
        newline();
        replaceTop(JsonScope.DANGLING_NAME);
    }

    private void beforeValue(boolean root)
    {
        switch (peek())
        {
        case EMPTY_DOCUMENT: // first in document
            if (!lenient && !root)
            {
                throw new IllegalStateException("JSON must start with an array or an object.");
            }
            replaceTop(JsonScope.NONEMPTY_DOCUMENT);
            break;

        case EMPTY_ARRAY: // first in array
            replaceTop(JsonScope.NONEMPTY_ARRAY);
            newline();
            break;

        case NONEMPTY_ARRAY: // another in array
            sb.append(',');
            newline();
            break;

        case DANGLING_NAME: // value for name
            sb.append(separator);
            replaceTop(JsonScope.NONEMPTY_OBJECT);
            break;

        case NONEMPTY_DOCUMENT:
            throw new IllegalStateException("JSON must have only one top-level value.");

        default:
            throw new IllegalStateException("Nesting problem: " + stack);
        }
    }

    public String close()
    {
        return sb.toString();
    }
}
