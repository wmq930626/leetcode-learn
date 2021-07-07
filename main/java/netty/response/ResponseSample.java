package netty.response;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: MingShi
 * @version: 2021/7/7
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSample {
    private String data;
    private String code;
    private long timestamp;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
