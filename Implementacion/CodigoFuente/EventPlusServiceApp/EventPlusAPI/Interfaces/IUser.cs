using EventPlusAPI.Entities;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IUser
    {
        UserEntity Authenticate(string username, string password);
        IEnumerable<UserEntity> GetAll();
    }
}
