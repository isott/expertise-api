package io.isott.expertise.infra.adapter.expertise.rest;

import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.usecase.RetrieveLatestExpertise;
import io.isott.expertise.infra.adapter.expertise.rest.dto.ExpertiseRequest;
import io.isott.expertise.infra.adapter.expertise.rest.dto.ExpertiseResponse;
import io.isott.expertise.infra.common.rest.BaseController;
import io.isott.expertise.infra.common.rest.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/expertises")
@RequiredArgsConstructor
public class ExpertiseController extends BaseController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<ExpertiseResponse> getExpertiseByCarId(@RequestParam @Valid @NotNull String carId) {
        var expertise = publish(Expertise.class, RetrieveLatestExpertise.from(carId));
        log.info("Expertise is retrieved. CarId: {}, Expertises: {}", carId, expertise);
        return respond(ExpertiseResponse.from(expertise));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<ExpertiseResponse> createExpertise(@RequestBody @Valid ExpertiseRequest expertiseRequest) {
        var expertise = publish(Expertise.class, expertiseRequest.toUseCase());
        log.info("Expertise is created: {}", expertise);
        return respond(ExpertiseResponse.from(expertise));
    }
}
