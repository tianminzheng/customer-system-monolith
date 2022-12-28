package org.geekbang.projects.cs.infrastructure.exception;

public interface ExceptionMessage {
    /**
     * 获取异常的 异常编码code
     *
     * @return exception code
     */
    MessageCode getMessageCode();
}
