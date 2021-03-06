﻿namespace EventPlusAPI.ViewModel
{
    public class UserTokenViewModel
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string Token { get; set; }
        public byte[] Image { get; set; }
        public string Email { get; set; }
    }
}
