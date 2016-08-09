package com.github.approval.converters;
/*
 * #%L
 * com.github.nikolavp:approval-json
 * %%
 * Copyright (C) 2014 Nikolavp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * This class is a standard jackson mixin which offers an indirect way
 * to add annotations to ignore or do something else with the members
 * of the entity you want to serialize without actually touching it.
 *
 * This mixin simply ignores date, age and first name which
 * we do not want to test of the Entity object!
 *
 * Learn more at: {@linktourl http://wiki.fasterxml.com/JacksonMixInAnnotations}
 *
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov@gmail.com>
 */
public abstract class EntityMixin1 {

    @JsonIgnore abstract Date getDate();

    @JsonIgnore abstract int getAge();

    @JsonIgnore abstract String getFirstName();
}
