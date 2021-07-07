package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.handler.ExceptionHandler;
import netty.handler.SampleInBoundHandler;
import netty.handler.SampleOutBoundHandler;

import java.net.InetSocketAddress;

/**
 * @author: MingShi
 * @version: 2021/7/5
 * @description:
 */
public class HttpServer01 {

    public static void main(String[] args) throws Exception {
        new HttpServer01().start(8088);
    }

    public static void start(int port) throws Exception {
        // 主从多线程模型
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 构造服务端
            ServerBootstrap b = new ServerBootstrap();
            // 绑定线程
            b.group(bossGroup, workerGroup)
                    // channel初始化
                    .channel(NioServerSocketChannel.class)
                    // 绑定端口
                    .localAddress(new InetSocketAddress(port))
                    // 注册channelHandler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new SampleInBoundHandler("SampleInBoundHandlerA", false))
                                    .addLast(new SampleInBoundHandler("SampleInBoundHandlerB", false))
                                    .addLast(new SampleInBoundHandler("SampleInBoundHandlerC", true))
                                    .addLast(new ExceptionHandler());
                            ch.pipeline()
                                    .addLast(new SampleOutBoundHandler("SampleOutBoundHandlerA"))
                                    .addLast(new SampleOutBoundHandler("SampleOutBoundHandlerB"))
                                    .addLast(new SampleOutBoundHandler("SampleOutBoundHandlerC"));

                        }
                    });
            // 设置channel参数
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind().sync();
            System.out.println("Http Server started， Listening on " + port);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}








