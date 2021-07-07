package netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Channel 被注册到 EventLoop");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Channel 从 EventLoop 中取消注册");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Channel 处于就绪状态，可以被读写");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Channel 处于非就绪状态Channel 可以从远端读取到数据");
        super.channelInactive(ctx);
    }

    @Override

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
        String content = String.format("Receive http request, uri: %s, method: %s, content: %s%n", msg.uri(), msg.method(), msg.content().toString(CharsetUtil.UTF_8));
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes()));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}
