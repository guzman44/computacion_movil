using EventPlusAPI.ViewModel;
using System.Collections.Generic;
using System.Linq;

namespace EventPlusAPI.Helpers
{
    public static class ExtensionMethods
    {

        public static IEnumerable<UserTokenViewModel> WithoutPasswords(this IEnumerable<UserTokenViewModel> users)
        {
            return users.Select(x => x.WithoutPassword());
        }

        public static UserTokenViewModel WithoutPassword(this UserTokenViewModel user)
        {
            user.Password = null;
            return user;
        }
    }
}
