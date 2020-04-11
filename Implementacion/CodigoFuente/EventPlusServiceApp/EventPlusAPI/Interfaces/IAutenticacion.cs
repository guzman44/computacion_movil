using EventPlusAPI.ViewModel;

namespace EventPlusAPI.Interfaces
{
    public interface IAutenticacion
    {
        UserTokenViewModel Authenticate(string username, string password);
        ResponseViewModel CreateAccount(CreateAccountViewModel model);
        ResponseViewModel ChangePassword(ChangePasswordAccountViewModel model);
        ResponseViewModel UpdatePerfilUser(PerfilAccountViewModel model);
    }
}
