using EventPlusAPI.Dtos;

namespace EventPlusAPI.Interfaces
{
    public interface IAutenticacion
    {
        UserEntity Authenticate(string username, string password);
        
    }
}
