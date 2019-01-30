TARGETS = console-setup resolvconf mountkernfs.sh ufw x11-common screen-cleanup apparmor hostname.sh plymouth-log udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh checkroot.sh lvm2 checkfs.sh open-iscsi networking iscsid urandom mountdevsubfs.sh mountall-bootclean.sh mountall.sh bootmisc.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountnfs.sh procps kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
open-iscsi: networking iscsid
networking: resolvconf mountkernfs.sh urandom procps
iscsid: networking
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh mountnfs-bootclean.sh udev
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
procps: mountkernfs.sh udev
kmod: checkroot.sh
