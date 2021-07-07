package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import netty.response.ResponseSample;

public class RequestSampleHandler extends ChannelInboundHandlerAdapter {

  @Override

  public void channelRead(ChannelHandlerContext ctx, Object msg) {

    String data = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);

    ResponseSample response = new ResponseSample("OK", data, System.currentTimeMillis());

    ctx.channel().writeAndFlush(response);

  }

}
