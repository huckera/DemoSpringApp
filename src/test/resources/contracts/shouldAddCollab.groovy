package contracts

org.springframework.cloud.contract.spec.Contract.make {
  description('''
  given:
  	collaborator data
  when:
  	server is operational
  then:
  	the created collaborator's ID is returned
  ''')
  request {
    method 'POST'
    url '/ao/addcollab'
    body(
      name: "Luca Marini",
      email: "lmarini@ao.com"
    )
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status 201
    body(
      id: 1
    )
    headers {
      contentType(applicationJson())
    }
  }
}
