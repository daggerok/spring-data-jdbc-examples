package contracts.web

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            priority(1)
            description('2020010401 - should return index.html page')
            request {
                url '/'
                method(GET())
            }
            response {
                status(OK())
                headers {
                    contentType(textHtml())
                }
                bodyMatchers {
                    '<title>TwitterOK</title>'
                }
            }
        },
        Contract.make {
            priority(1)
            description('2020010401 - should use /404 path on any error')
            request {
                url '/error'
                method(GET())
            }
            response {
                status(NOT_FOUND())
            }
        },
        Contract.make {
            priority(1)
            description('2020010401 - should return OK on /404 path')
            request {
                url '/404'
                method(GET())
            }
            response {
                status(OK())
            }
        },
]
