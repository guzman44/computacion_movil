using EventPlusAPI.ViewModel;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IUsuario
    {
        List<UserViewModel> GetAll();
        UserViewModel GetbyId(long id);
        UserViewModel GetbyUsername(string username);
    }
}
