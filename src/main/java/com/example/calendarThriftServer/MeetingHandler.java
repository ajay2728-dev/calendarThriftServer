package com.example.calendarThriftServer;

import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;


@Service
public class MeetingHandler implements IMeetingService.Iface {

    @Override
    public boolean canScheduleMeeting(IMeetingServiceDTO meetingDTO) throws TException {
        return true;
    }
}
