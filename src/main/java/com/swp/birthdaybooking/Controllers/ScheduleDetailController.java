package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreateScheduleDetailRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.entities.ScheduleDetail;
import com.swp.birthdaybooking.services.ScheduleDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/schedule-detail")
public class ScheduleDetailController {
    private final ScheduleDetailService scheduleDetailService;

    public ScheduleDetailController(ScheduleDetailService scheduleDetailService) {
        this.scheduleDetailService = scheduleDetailService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getScheduleDetail() {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule details retrieved",
                        scheduleDetailService.getAllScheduleDetails()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getScheduleDetailById(@PathVariable Integer id) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule detail retrieved",
                        scheduleDetailService.getScheduleDetailById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createScheduleDetail(CreateScheduleDetailRq scheduleDetail) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule detail created",
                        scheduleDetailService.createScheduleDetail(scheduleDetail)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateScheduleDetail(ScheduleDetail scheduleDetail, @PathVariable String id) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Schedule detail updated",
                        scheduleDetailService.updateScheduleDetail(scheduleDetail)));
    }

}
