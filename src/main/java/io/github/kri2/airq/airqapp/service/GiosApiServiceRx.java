package io.github.kri2.airq.airqapp.service;

import io.github.kri2.airq.airqapp.response.DateAndValue;
import rx.Single;

import java.util.Set;

public interface GiosApiServiceRx
{
    Single<Set<DateAndValue>> findAll();
}
