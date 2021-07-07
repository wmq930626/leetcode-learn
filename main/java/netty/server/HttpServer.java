package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import netty.handler.HttpServerHandler;

import java.net.InetSocketAddress;

/**
 * @author: MingShi
 * @version: 2021/7/5
 * @description:
 */
public class HttpServer {

    public static void main(String[] args) throws Exception{
        new HttpServer().start(8088);
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
                                    .addLast("codec", new HttpServerCodec())         // HTTP 编解码
                                    .addLast("compressor", new HttpContentCompressor())   // HttpContent 压缩
                                    .addLast("aggregator", new HttpObjectAggregator(65536)) // HTTP 消息聚合
                                    .addLast("handler", new HttpServerHandler());      // 自定义业务逻辑处理器
                        }
                    });
            /**
             * SO_KEEPALIVE	设置为 true 代表启用了 TCP SO_KEEPALIVE 属性，TCP 会主动探测连接状态，即连接保活
             * SO_BACKLOG	已完成三次握手的请求队列最大长度，同一时刻服务端可能会处理多个连接，在高并发海量连接的场景下，
             *              该参数应适当调大
             * TCP_NODELAY	Netty 默认是 true，表示立即发送数据。如果设置为false表示启用 Nagle 算法，该算法会将
             *              TCP 网络数据包累积到一定量才会发送，虽然可以减少报文发送的数量，但是会造成一定的数据延迟。
             *              Netty为了最小化数据传输的延迟，默认禁用了Nagle算法
             * SO_SNDBUF	TCP数据发送缓冲区大小
             * SO_RCVBUF	TCP数据接收缓冲区大小
             * SO_LINGER	设置延迟关闭的时间，等待缓冲区中的数据发送完成
             * CONNECT_TIMEOUT_MILLIS	建立连接的超时时间
             */
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
