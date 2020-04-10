using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Login
    {
        public int Id { get; set; }
        public string UserName { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string Activo { get; set; }
    }
}
