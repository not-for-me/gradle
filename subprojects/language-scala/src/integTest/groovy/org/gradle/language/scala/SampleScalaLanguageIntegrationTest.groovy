/*
 * Copyright 2014 the original author or authors.
 *
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
 */

package org.gradle.language.scala

import org.gradle.integtests.fixtures.AbstractIntegrationSpec
import org.gradle.integtests.fixtures.Sample
import org.gradle.test.fixtures.archive.JarTestFixture
import org.junit.Rule

class SampleScalaLanguageIntegrationTest extends AbstractIntegrationSpec {

    @Rule
    Sample sample = new Sample(temporaryFolder, "jvmComponents/scala")

    def setup() {
        executer.withRepositoryMirrors()
    }

    def "can build scala based jvm component"() {
        setup:
        executer.inDirectory(sample.dir)

        when:
        succeeds("assemble")

        then:
        new JarTestFixture(sample.dir.file("build/jars/main/jar/main.jar")).hasDescendants(
                "org/gradle/samples/HelloWorld.class",
                "org/gradle/samples/HelloWorld\$.class",
                "org/gradle/samples/Greeter.class",
                "org/gradle/samples/Person.class"
        )
    }
}
