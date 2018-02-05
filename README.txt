MailManage read an email, execute command which are in it and send result back.

Exmaple email:

BEGIN#
pwd#
echo HELLO WORLD!!!#
ifconfig#
END#

Answer:

out:
/home/rpark/Mailer
HELLO WORLD!!!
eno1: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet xxx.xxx.xxx.xxx  netmask xxx.xxx.xxx.xxx  broadcast xxx.xxx.xxx.xxx
        inet6 e80::f90:9ca2:da48:1781  prefixlen 64  scopeid 0x20<link>
        ether 80:52:d7:43:1c:d3  txqueuelen 1000  (Ethernet)
        RX packets 2762355  bytes 420575091 (401.0 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 449763  bytes 129386970 (123.3 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
        device interrupt 20  memory 0xfe300000-fe320000

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1  (Local Loopback)
        RX packets 448357  bytes 230113991 (219.4 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 448357  bytes 230113991 (219.4 MiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

virbr0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        inet xxx.xxx.xxx.xxx  netmask xxx.xxx.xxx.xxx  broadcast xxx.xxx.xxx.xxx
        ether 80:52:d7:43:1c:d3  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

errors:

