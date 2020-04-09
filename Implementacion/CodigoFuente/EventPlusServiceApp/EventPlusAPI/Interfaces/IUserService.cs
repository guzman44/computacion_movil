using EventPlusAPI.Entities;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IUserService
    {
        UserEntity Authenticate(string username, string password);
        IEnumerable<UserEntity> GetAll();
    }
}
