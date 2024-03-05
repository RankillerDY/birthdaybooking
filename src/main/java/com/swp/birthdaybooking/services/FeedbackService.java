package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.entities.Feedback;
import com.swp.birthdaybooking.exception.NotFoundException;
import com.swp.birthdaybooking.repositories.FeedbackRepository;
import com.swp.birthdaybooking.repositories.ServiceBirthdayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ServiceBirthdayRepository serviceBirthdayRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, ServiceBirthdayRepository serviceBirthdayRepository) {
        this.feedbackRepository = feedbackRepository;
        this.serviceBirthdayRepository = serviceBirthdayRepository;
    }

    /**
     * View feedback
     */
    public List<Feedback> getByServiceId(int serviceId) {
        var isServiceExist = serviceBirthdayRepository.existsById(serviceId);
        if(!isServiceExist) throw new NotFoundException("Can not found service with id: " + serviceId + "!");

        var feedbacks = feedbackRepository
                .findAllByServiceId(serviceId);
        return feedbacks.orElseGet(List::of);
    }
}
