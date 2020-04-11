using System.ComponentModel.DataAnnotations;

namespace EventPlusAPI.ViewModel
{
    public class AuthenticateViewModel
    {
        [Required]
        public string Username { get; set; }

        [Required]
        public string Password { get; set; }
    }
}
