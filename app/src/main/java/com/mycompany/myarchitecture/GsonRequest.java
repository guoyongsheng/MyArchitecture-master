package com.mycompany.myarchitecture;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mycompany.myarchitecture.util.GsonUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by lenovo on 2016/8/20.
 *
 * 自定义request
 */
public class GsonRequest<T> extends Request<T>
{
    private Response.Listener listener;
    private Type type;

    public GsonRequest(int method, String url, Type type, Response.Listener listener, Response.ErrorListener errorListener)
    {
        super(method, url, errorListener);
        this.type = type;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            T result;
            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            result = GsonUtils.getInstance().fromJson(jsonStr, type);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response)
    {
        if(listener != null)
        {
            listener.onResponse(response);
        }
    }
}
