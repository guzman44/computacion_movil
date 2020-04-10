using EventPlusAPI.Dao;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IEvento
    {
        List<Evento> GetAll();
    }
}
