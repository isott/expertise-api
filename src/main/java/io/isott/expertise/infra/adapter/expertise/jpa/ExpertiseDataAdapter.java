package io.isott.expertise.infra.adapter.expertise.jpa;

import io.isott.expertise.domain.common.exception.BusinessException;
import io.isott.expertise.domain.common.model.Status;
import io.isott.expertise.domain.expertise.model.Answer;
import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.port.ExpertisePort;
import io.isott.expertise.domain.expertise.usecase.CreateExpertise;
import io.isott.expertise.infra.adapter.expertise.jpa.entity.AnswerEntity;
import io.isott.expertise.infra.adapter.expertise.jpa.entity.ExpertiseEntity;
import io.isott.expertise.infra.adapter.expertise.jpa.entity.PhotoEntity;
import io.isott.expertise.infra.adapter.expertise.jpa.entity.QuestionEntity;
import io.isott.expertise.infra.adapter.expertise.jpa.repository.ExpertiseJpaRepository;
import io.isott.expertise.infra.adapter.expertise.jpa.repository.QuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.isott.expertise.domain.expertise.model.Answer.IssueStatus.YES;

@Service
@RequiredArgsConstructor
public class ExpertiseDataAdapter implements ExpertisePort {

    private final ExpertiseJpaRepository expertiseJpaRepository;
    private final QuestionJpaRepository questionJpaRepository;

    @Override
    public Expertise retrieveLatestExpertiseByCarId(String carId) {
        var latestExpertise = expertiseJpaRepository.findLatestByCarId(carId);

        if (latestExpertise == null) {
            return Expertise.empty();
        }

        return latestExpertise.toModel();
    }

    @Override
    public Expertise createExpertise(CreateExpertise createExpertise) {
        ExpertiseEntity expertiseEntity = new ExpertiseEntity();
        expertiseEntity.setCarId(createExpertise.getCarId());
        expertiseEntity.setStatus(Status.ACTIVE);

        expertiseEntity.setAnswers(createExpertise.getAnswers().stream()
                .map(answer -> createAnswerEntity(answer, expertiseEntity))
                .toList());

        return expertiseJpaRepository.save(expertiseEntity).toModel();
    }

    private AnswerEntity createAnswerEntity(Answer answer, ExpertiseEntity expertiseEntity) {
        var answerEntity = new AnswerEntity();
        answerEntity.setExpertise(expertiseEntity);
        answerEntity.setDescription(answer.getDescription());

        var questionEntity = getQuestionEntity(answer.getQuestionId());
        answerEntity.setQuestion(questionEntity);

        var hasIssue = answer.getHasIssue();
        answerEntity.setHasIssue(hasIssue);

        if (hasIssue == YES) {
            var photoEntities = answer.getPhotoUrls().stream()
                    .map(photoUrl -> createPhotoEntity(photoUrl, answerEntity))
                    .toList();

            answerEntity.setPhotos(photoEntities);
        }
        answerEntity.setStatus(Status.ACTIVE);

        return answerEntity;
    }

    private QuestionEntity getQuestionEntity(Long id) {
        var questionEntityOptional = questionJpaRepository.findById(id);
        if (questionEntityOptional.isEmpty()) {
            throw new BusinessException("question.error.notFound");
        }

        return questionEntityOptional.get();
    }

    private PhotoEntity createPhotoEntity(String photoUrl, AnswerEntity answerEntity) {
        var photoEntity = new PhotoEntity();
        photoEntity.setAnswer(answerEntity);
        photoEntity.setImageUrl(photoUrl);
        photoEntity.setStatus(Status.ACTIVE);

        return photoEntity;
    }
}
