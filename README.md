# superheros-micro
Superhero maintenance application

# Description
This microservice allows you to consult, update and delete information related to superheroes. It belongs to the Zeraus application (W2M group)

## Endpoints
### GET /zeraus/findAll
Method to find the superheroes. It has pagination and, by default, the size of each page is 3 records

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `pageNumber` | optional | number  | Number of page                                                                     |
|     `pageSize` | optional | number  | Size of page |      

### GET /zeraus/findById
Method for find superhero by id.

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `id` | required | number  | Id of the superhero                                                                     | 

### GET /zeraus/findByName
Method for find all the superheroes by the name or a part of the name. It has pagination and, by default, the size of each page is 3 records.

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `name` | required | String(25)  | Value of name                                                                 |
|     `pageNumber` | optional | number  | Number of page                                                                     |
|     `pageSize` | optional | number  | Size of page |   

### PUT /zeraus/superhero
Method for update the superhero.

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `id` | required | number  |  Id of the superhero                                                                |
|     `name` | required | String(25)  | Name of superhero                                                                     |
|     `gender` | required | String(1)  | Gender of superhero. Possible values: M or F |   

### DELETE /zeraus/{id}
Method for delete the superhero by id.

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `id` | required | number  | Id of the superhero                                                                     | 
