using EventPlusAPI.Dtos;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IAutenticacion
    {
        UserEntity Authenticate(string username, string password);
        IEnumerable<UserEntity> GetAll();
    }
}
