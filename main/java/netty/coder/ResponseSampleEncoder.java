package netty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.response.ResponseSample;

public class ResponseSampleEncoder extends MessageToByteEncoder<ResponseSample> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseSample msg, ByteBuf out) {
        if (msg != null) {
            out.writeBytes(msg.getCode().getBytes());
            out.writeBytes(msg.getData().getBytes());
            out.writeLong(msg.getTimestamp());
        }
    }
}