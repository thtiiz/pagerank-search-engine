<!--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 -->
<%@include file="header.jsp"%>
<%@page pageEncoding="UTF-8"%>
    <div class="container row align-items-center text-center h-100 mx-auto">
    <div class="col my-auto">
        <img class="mb-4" src="https://image.freepik.com/free-vector/tiny-hr-manager-looking-candidate-job-interview-magnifier-computer-screen-flat-vector-illustration-career-employment_74855-8619.jpg" />
    <form name="search" action="results" method="get">
    <input autofocus placeholder="Search..." class="form-control" name="query" required/>
    <input class="btn btn-primary btn-block mt-4" type="submit" value="Search">
    </form>
    <%@include file="footer.jsp"%>
    </div>
    </div>


