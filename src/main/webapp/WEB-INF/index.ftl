<html>
<head><title>Hello World</title>
 
<body>
  <form name="user" action="hello" method="post">
    Firstname: <input type="text" name="firstname" /> <br/>
    Lastname: <input type="text" name="lastname" /> <br/>
    <input type="submit" value="Save" />
  </form>
 
  <table class="datatable">
    <tr>
        <th>Firstname</th>  <th>Lastname</th>
    </tr>
    <#list usersxx as userf>
    <tr>
        <td>${userf.firstname}</td> <td>${userf.lastname}</td>
    </tr>
    </#list>
  </table>
</body>
</html>