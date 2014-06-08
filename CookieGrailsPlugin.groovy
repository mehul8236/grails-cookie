/*
 * Copyright 2012 the original author or authors
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

/**
 * @author <a href='mailto:dale@dalew.com'>Dale Wiggins</a>
 * @author <a href='mailto:stokito@gmail.com'>Sergey Ponomarev</a>
 */
class CookieGrailsPlugin {

    def version = '0.51'
    def grailsVersion = '2.1 > *'
    def pluginExcludes = [
            'grails-app/views/error.gsp'
    ]
    def author = 'Dale Wiggins'
    def authorEmail = 'dale@dalew.com'
    def title = 'Cookie Plugin'
    def description = 'Makes dealing with cookies easy. Provides an injectable service and expands request with methods to easily get, set, and delete cookies with one line'

    def cookieService = new com.dalew.CookieService()

    def observe = ['controllers']

    def documentation = 'https://github.com/dalew75/grails-cookie'

    def license = 'APACHE'

    def developers = [
            [name: 'Dale Wiggins', email: 'dale@dalew.com'],
            [name: 'Sergey Ponomarev', email: 'stokito@gmail.com']
    ]

    def issueManagement = [system: 'GITHUB', url: 'https://github.com/dalew75/grails-cookie/issues']
    def scm = [url: 'https://github.com/dalew75/grails-cookie']

    def doWithDynamicMethods = { applicationContext ->
        extendReqResp()
    }

    def onChange = { event ->
        extendReqResp()
    }

    void extendReqResp() {
        javax.servlet.http.HttpServletRequest.metaClass.getCookie = { String name ->
            return cookieService.getCookie(name)
        }
        javax.servlet.http.HttpServletResponse.metaClass.setCookie = { String name, String value, Integer maxAge = null ->
            return cookieService.setCookie(name, value, maxAge)
        }
        javax.servlet.http.HttpServletResponse.metaClass.deleteCookie = { String name ->
            return cookieService.deleteCookie(name)
        }
    }

}
