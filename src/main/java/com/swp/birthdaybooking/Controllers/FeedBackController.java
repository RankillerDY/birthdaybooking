package com.swp.birthdaybooking.Controllers;


import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.services.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/feedback")
public class FeedBackController {
    private final FeedbackService feedbackService;

    public FeedBackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getByServiceId(int serviceId) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Feedback retrieved",
                        feedbackService.getByServiceId(serviceId)));
    }
}
