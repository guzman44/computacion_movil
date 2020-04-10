using EventPlusAPI.Dao;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace EventPlusAPI.Interfaces
{
    public interface IEvento
    {
        List<Evento> GetAll();
    }
}
