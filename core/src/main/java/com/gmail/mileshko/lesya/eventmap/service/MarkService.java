package com.gmail.mileshko.lesya.eventmap.service;

import com.gmail.mileshko.lesya.eventmap.dto.MarkDto;
import com.gmail.mileshko.lesya.eventmap.entity.Mark;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.repository.MarkRepository;
import com.gmail.mileshko.lesya.eventmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarkService {

    private final MarkRepository markRepository;
    private final UserRepository userRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, UserRepository userRepository) {
        this.markRepository = markRepository;
        this.userRepository = userRepository;
    }

    public void saveMark(MarkDto markDto) throws NoSuchEntityException {

        Mark mark = new Mark();

        User user = userRepository.findById(markDto.user.getId())
                .orElseThrow(()-> new NoSuchEntityException("user not found."));

        mark.setUser(user);
        mark.setLongitude(markDto.longitude);
        mark.setLatitude(markDto.latitude);

        markRepository.save(mark);
    }

    public List<Mark> getMarks() {
        return markRepository.findAll();
    }
}
