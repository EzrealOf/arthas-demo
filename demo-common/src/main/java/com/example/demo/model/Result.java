package com.example.demo.model;


import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static String SUCCESSMSG = "处理成功";
    private static String ERRORMSG = "操作失败";
    private static String ERRORCODE = "500";
    private static String SUCCESSCODE = "200";
    private Boolean status;
    private String msg;
    private String code;
    private T data;

    public static <T> Result<T> success(T data) {
        Boolean status = Boolean.TRUE;
        String code = SUCCESSCODE;
        String msg = SUCCESSMSG;
        return result(data, status, code, msg);
    }


    public static <T> Result<T> result(T data, Boolean status, Exception errorInfo) {
        return result(data, status, errorInfo.getMessage(), errorInfo.getLocalizedMessage());
    }

    private static <T> Result<T> result(T data, Boolean status, String code, String msg) {
        Result<T> result = new Result();
        result.setStatus(status);
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result.ResultBuilder<T> builder() {
        return new Result.ResultBuilder();
    }

    public static Result failed(Exception e) {
        Result<String> result = new Result();
        result.setStatus(false);
        result.setCode(ERRORCODE);
        result.setData(e.getLocalizedMessage());
        result.setMsg(e.getMessage());
        return result;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setStatus(final Boolean status) {
        this.status = status;
        return this;
    }

    public Result<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> setCode(final String code) {
        this.code = code;
        return this;
    }

    public Result<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label59;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label59;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "Result(status=" + this.getStatus() + ", msg=" + this.getMsg() + ", code=" + this.getCode() + ", data=" + this.getData() + ")";
    }

    public Result() {
    }

    public Result(final Boolean status, final String msg, final String code, final T data) {
        this.status = status;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static class ResultBuilder<T> {
        private Boolean status;
        private String msg;
        private String code;
        private T data;

        ResultBuilder() {
        }

        public Result.ResultBuilder<T> status(final Boolean status) {
            this.status = status;
            return this;
        }

        public Result.ResultBuilder<T> msg(final String msg) {
            this.msg = msg;
            return this;
        }

        public Result.ResultBuilder<T> code(final String code) {
            this.code = code;
            return this;
        }

        public Result.ResultBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            return new Result(this.status, this.msg, this.code, this.data);
        }

        public String toString() {
            return "Result.ResultBuilder(status=" + this.status + ", msg=" + this.msg + ", code=" + this.code + ", data=" + this.data + ")";
        }
    }
}

