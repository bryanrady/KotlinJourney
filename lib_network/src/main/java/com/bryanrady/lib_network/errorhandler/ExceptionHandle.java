package com.bryanrady.lib_network.errorhandler;

import androidx.annotation.Nullable;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * 统一错误处理
     * @param e
     * @return
     */
    public static ResponseException handleException(Throwable e){
        ResponseException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseException(e, Error.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";
                    break;
            }
        } else if (e instanceof ServerException) {
            //app的错误处理
            ServerException serverException = (ServerException) e;
            ex = new ResponseException(serverException, serverException.code);
            ex.message = serverException.message;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseException(e, Error.PARSE_ERROR);
            ex.message = "解析错误";
        } else if (e instanceof ConnectException) {
            ex = new ResponseException(e, Error.NETWORK_ERROR);
            ex.message = "连接错误";
        } else if (e instanceof SSLHandshakeException) {
            ex = new ResponseException(e, Error.SSL_ERROR);
            ex.message = "证书错误";
        } else if (e instanceof ConnectTimeoutException
                || e instanceof TimeoutException
                || e instanceof SocketTimeoutException) {
            ex = new ResponseException(e, Error.TIMEOUT_ERROR);
            ex.message = "连接超时错误";
        } else if (e instanceof UnknownHostException) {
            ex = new ResponseException(e, Error.UNKNOWN_HOST_ERROR);
            ex.message = "未知域名错误";
        } else {
            ex = new ResponseException(e, Error.UNKNOWN);
            ex.message = "未知错误";
        }
        return ex;
    }

    public static class ServerException extends RuntimeException {
        public int code;
        public String message;
    }

    /**
     * 给上层的统一错误响应
     */
    public static class ResponseException extends Exception {
        private int code;
        private String message;

        public ResponseException(Throwable e, int code){
            super(e);
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        @Nullable
        @Override
        public String getMessage() {
            return message;
        }
    }

    /**
     * 约定异常
     */
    public static class Error {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络连接错误
         */
        public static final int NETWORK_ERROR = 1002;
        /**
         * http错误
         */
        public static final int HTTP_ERROR = 1003;
        /**
         * 证书检验错误
         */
        public static final int SSL_ERROR = 1004;
        /**
         * 连接超时错误
         */
        public static final int TIMEOUT_ERROR = 1005;
        /**
         * 未知域名错误
         */
        public static final int UNKNOWN_HOST_ERROR = 1006;
    }

}
