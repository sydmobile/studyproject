README

personal study project,only use for me!

Thank you !


## f

如果每次读取都返回 `byte` ，有可能在读到中间的时候遇到 `11111111` 那么这个   `11111111` 是 byte 类型的 -1，
我们的程序是遇到 -1 就会停止不读了，后面的数据就读不完了，所以在读取的时候用
int 类型接受，如果 11111111 会在其前面补上 24 个 0 凑足 4 个字节，那么 byte
类型的 -1 就变成 int 类型的 255 了，这样可以保证整个数据读完，而结束标记的 -1 就是
类型了。
说的简单就是 byte 正好表示可以读取内容，然而还有个读取完毕标记，byte 类型的就容不下了。

### FileOutputStream

输出流，写。如果没有对应的文件就会自动创建一个。当然前提是路径是存在的，如果是相对路径那就没有问题了

```java
    // 会在创建对象的时候，如果没有这个文件会帮我们创建出来，如果有就清空内容
    FileOutputStream fos = new FileOutputSteam("xxx.txt");
    fos.write(100);
    fos.close();
    
    // 可以续写
    FileOutputStream fos = new FileOutputStream("xxx.txt",true);

```

### 拷贝图片

```java
        // 效率会非常低，要一个字节一个字节的进行读写。效率非常低。
        FileInputStream fis = new FileInputStream("xxx.jpg");
        FileOutputStream fos = new FileOutputStream("copy.jpg");
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fis.close();
        fos.close();

        
        
        FileInputStream fis = new FileInputStream("xxx.jpg");
        FileOutputStream fos = new FileOutputStream("copy.jpg");
        // 返回输入流中可以读取到的剩余字节数
        int len = fis.available();
        // 这种方式不推荐使用，因为有可能会导致内存溢出。如果数组过大
        byte[] arr = new byte[len];
        fis.read(arr);
        fos.write(arr);
        fis.close();
        fos.close();
        
        
        FileInputStream fis = new FileInputStream("xxx.jpg");
        FileOutputStream fos = new FileOutputStream("copy.jpg");
        // 返回输入流中可以读取到的剩余字节数
        byte[] arr = new byte[1024 * 10];
        int len;
        // 会返回读到的有效字节个数,新读到的内容会覆盖原先内容，如果原先 arr 数组中有值，
        // 新读到的内容就会从 0 开始覆盖，后面没有覆盖的还是 arr 原来的值
        while ((len = fis.read(arr)) != -1) {
            fos.write(arr, 0, len);
        }
        fis.close();
        fos.close();
```


### BufferedInputStream BufferedOutputStream

对普通的字节流的包装，让其更加强大。

```java
FileInputStream fis = new FileInputStream("xxx.mp3");
FileOutputStream fos = new FileOutputStream("copy.mp3");
BufferedInputStream bis = new BufferedInputStream(fis);
BufferedOutputStream bos = new BufferedOutputStream(fos);
int b;
while((b = bis.read())!=-1){
    bos.write(b);
}
// 只需要关闭包装对象就可以了
bis.close();
bos.close();
```

默认缓存是 8 个字节。图片，

红框内部是内存中的运行，会从缓存数组中一个一个地读取

自己定义小数组的读写和带 Buffered 的读取哪个更快？

- 定义小数组如果是 8192 个字节大小和 Buffered 比较的话
- 定义的小数组会略胜一筹，因为读和写操作的是同一个数组，而 Buffered 是两个数值

### flush 和 close

close 方法有刷新的功能，在关闭流之前会先刷新一次缓冲区，将缓冲区的字节全都刷新到文件上，再关闭。
当然如果缓冲区满了，也会刷新。

flush 方法
只是刷新

### 字节流读取中文

字节流在读中文的时候可能会读到半个中文，造成乱码。

字节流写出中文的问题
字节流直接操作的字节，所以写出中文必须将字符串转成字节数组  `"xxxx".getBytes()`
写出回车换行`write("\r\n".getBytes())`

### 解码、编码
将计算机语言变成我们看的懂的，解码，就是 将 byte 变成 String 。String 中有这样的构造方法

### JDK7 流异常处理
```java
    
    try(
        FileInputStream fis = new FileInputSream("xxx.txt");    
        FileOutputStream fos = new FileOutputStream("yyy.txt");
        
    ){
        int b;
        while((b = fis.read()) != -1){
            fos.write(b);
        }
    }
    
    会自动关闭，需要类实现 AutoCloseable 
```

### 字符流

字符流是可以直接读写字符的 IO 流
字符流读取字符，就要先读取字节数据，然后转为字符，如果要写出字符，需要把字符转为字节再写出
