# 常用命令

1. 读取文件：cat指令，cat -n显示行号。  cat file|more一页一页查看文件，按空格翻页，回车下一行。less file一页一页查看文件，只不过less是一页一页加载文件，而不是一下子加载完成（空格下一页，回车下一行，pgup上一页，ugdn下一页）。
2. 查找文件：find指令，find  /usr -name test1.txt   以test1.txt的名字在/usr的目录下查找文件，find /usr/test -user root在/usr/test目录下查找用户root创建的文件 ，find /usr -size +10M查找/usr目录下大小大于10M的文件     locate使用之前要先updatedb指令，然后查找文件。
3. 权限管理：chmod o-x file.sh  给其他用户减去对file.sh的执行权限（u代表文件所有者，g代表文件所在组，o代表其他人，a代表所有人，也可以不用加减号而用等于号如chmod u=rwx,g=rx,o=r管理权限）。groups查看当前用户所属组。
4. 查看磁盘状态：df -h
5. yum search mysql  到yum中搜寻mysql  yum源是yum命令去哪里取安装包的地图；阿里配置yum源：https://developer.aliyun.com/mirror/   配置镜像是为了让下载更快

# 磁盘管理

1. 磁盘命名与分区：几乎所有的硬件设备都在/dev目录下
   第一个打印机 /dev/lp0 
   第一块软盘/dev/fd0
   第一块硬盘 /dev/sda   第一个分区 dev/sda1 第二个分区 dev/sda2
   第二块硬盘 /dev/sdb
   usb /dev/sd[a-p]
   虚拟机内 /dev/vd[a-p]
   tmpfs使linux内核维持的虚拟文件系统，它使用操作系统页缓存存储文件数据。
   一个磁盘可以分为多个分区 
2. kvm学习：https://program-think.blogspot.com/2020/06/Linux-Logical-Volume-Manager.html