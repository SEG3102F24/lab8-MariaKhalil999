package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import seg3x02.booksapigraphql.entity.Employee
import seg3x02.booksapigraphql.repository.EmployeesRepository
import seg3x02.booksapigraphql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver(val mongoOperations: MongoOperations, private val employeesRepository: EmployeesRepository) 
{
    @QueryMapping
    fun employees(): List<Employee> {
        return employeesRepository.findAll()
    }

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput) : Employee {
        val employee = Employee(input.name, input.dateOfBirth, input.city, input.salary, input.gender, input.email)
        employeesRepository.save(employee)
        return employee
    }

}

