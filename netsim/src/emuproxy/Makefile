#get all of the env vars and user options
ifneq ($(INCLUDED_INC), true)
include /home/liux/primogeni/netsim/Makefile.inc 
endif

# get the rules to build the ssfnet code
ifneq ($(INCLUDED_SSFNET), true)
include /home/nmvanvo/workspace/primex/netsim/src/ssfnet/Makefile 
endif

OPENVPNDIR=/home/liux/primogeni/netsim/src/emuproxy/openvpn
SSFGWDDIR=/home/liux/primogeni/netsim/src/emuproxy/ssfgwd

SSFGWD_HDRFILES = \
	$(SSFGWDDIR)/circle_buffer.h \
	$(SSFGWDDIR)/debug.h \
	$(SSFGWDDIR)/fd.h \
	$(SSFGWDDIR)/ipframe_utils.h \
	$(SSFGWDDIR)/listener.h \
	$(SSFGWDDIR)/select_call.h \
	$(SSFGWDDIR)/ssfgwd_error.h \
	$(SSFGWDDIR)/ssfgwd_route.h \
	$(SSFGWDDIR)/tun.h
SSFGWD_SRCFILES = \
	$(SSFGWDDIR)/circle_buffer.cc \
	$(SSFGWDDIR)/fd.cc \
	$(SSFGWDDIR)/ipframe_utils.cc \
	$(SSFGWDDIR)/listener.cc \
	$(SSFGWDDIR)/select_call.cc \
	$(SSFGWDDIR)/ssfgwd.cc \
	$(SSFGWDDIR)/ssfgwd_error.cc \
	$(SSFGWDDIR)/ssfgwd_route.cc \
	$(SSFGWDDIR)/tun.cc


SSFGWD_OBJECTS = $(SSFGWD_SRCFILES:.cc=.gwd.o)
SSFGWD_INCLUDES = -I$(NETSIM_DIR)/src -I$(SSFGWDDIR) $(SSF_INCLUDES)
SSFGWD_CFLAGS = $(SSF_CXXFLAGS)
SSFGWD_LDFLAGS =
SSFGWD_LDADD =

SSFGWD_EXE=/home/liux/primogeni/netsim/ssfgwd
OPENVPN_EXE=/home/liux/primogeni/netsim/openvpn
OPENVPN_SO=/home/liux/primogeni/netsim/openvpn-client-conn.so

%.gwd.o:	%.cc $(SSFGWD_HDRFILES)
	$(CXX_ORIG) $(SSFGWD_INCLUDES) $(SSFGWD_CFLAGS) $(SSFNET_INCLUDES) $(SSFGWD_CFLAGS) -o $@ -c $<

emuproxy-help:
	@ $(ECHO) "    emuproxy          :  Build the ssfnet emulation proxy."
	@ $(ECHO) "    emuproxy-clean    :  Clean the ssfnet emulation proxy."

ifeq ($(ENABLE_SSFNET_OPENVPN), yes)
emuproxy: $(SSFGWD_EXE) $(OPENVPN_EXE) $(OPENVPN_SO)
emuproxy-clean:	
	$(RMV) $(SSFGWD_OBJECTS) $(SSFGWD_EXE) $(OPENVPN_EXE) $(OPENVPN_SO)
	(cd $(OPENVPNDIR); make clean)
	(cd $(OPENVPNDIR)/plugin/client-conn; make clean)
else
emuproxy:
emuproxy-clean:	
endif

$(SSFGWD_EXE):	$(SSFGWD_OBJECTS)
	$(CXX_ORIG) $(SSFGWD_OBJECTS) -o $@ $(SSFGWD_LDFLAGS) $(SSFGWD_LDADD)

$(OPENVPN_EXE) $(OPENVPN_SO):	$(OPENVPNDIR) $(OPENVPNDIR)/plugin/client-conn
	(cd $(OPENVPNDIR); make)
	(cd $(OPENVPNDIR)/plugin/client-conn; make)
	ln -sf $(OPENVPNDIR)/openvpn $(OPENVPN_EXE)
	ln -sf $(OPENVPNDIR)/plugin/client-conn/openvpn-client-conn.so $(OPENVPN_SO)

INCLUDED_EMUPROXY=true
