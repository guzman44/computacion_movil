using System;
using EventPlusAPI.Helpers;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.Extensions.Configuration;

namespace EventPlusAPI.Dao
{
    public partial class EventPlusContext : DbContext
    {
        public IConfiguration Configuration { get; }

        public EventPlusContext()
        {
        }

        public EventPlusContext(DbContextOptions<EventPlusContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Categoria> Categoria { get; set; }
        public virtual DbSet<Evento> Evento { get; set; }
        public virtual DbSet<EventoUsuario> EventoUsuario { get; set; }
        public virtual DbSet<Localizacion> Localizacion { get; set; }
        public virtual DbSet<Login> Login { get; set; }
        public virtual DbSet<ParametrizacionObjetos> ParametrizacionObjetos { get; set; }
        public virtual DbSet<Publicaciones> Publicaciones { get; set; }
        public virtual DbSet<Usuario> Usuario { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                var appSettingsSection = Configuration.GetSection("AppSettings");
                var appSettings = appSettingsSection.Get<AppSettings>();
                optionsBuilder.UseSqlServer(appSettings.ConnectionDB);
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Categoria>(entity =>
            {
                entity.HasComment("Tabla que contiene la informaci??e los Categoria");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))")
                    .HasComment("Si es eliminado o no (1=activo y 0=Eliminado)");

                entity.Property(e => e.FechaRegistro)
                    .HasColumnType("datetime")
                    .HasComment("Fecha de registro del like o Seguir");

                entity.Property(e => e.IdCategoria)
                    .HasColumnName("Id_Categoria")
                    .HasComment("Llave Foranea");

                entity.Property(e => e.IdEvento)
                    .HasColumnName("Id_Evento")
                    .HasComment("Llave Foranea");

                entity.Property(e => e.IdLogin)
                    .HasColumnName("Id_Login")
                    .HasComment("Llave Foranea");
            });

            modelBuilder.Entity<Evento>(entity =>
            {
                entity.HasComment("Tabla que contiene la informaci??e la Evento");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))")
                    .HasComment("Si activo en el sistema");

                entity.Property(e => e.Descripcion)
                    .IsRequired()
                    .HasMaxLength(250)
                    .IsUnicode(false)
                    .HasComment("Descripci??el evento");

                entity.Property(e => e.FechaFin)
                    .HasColumnType("datetime")
                    .HasComment("Fecha Fin");

                entity.Property(e => e.FechaInicio)
                    .HasColumnType("datetime")
                    .HasComment("Fecha de Inicio del evento");

                entity.Property(e => e.Imagen)
                    .HasColumnType("image")
                    .HasComment("Imagen en miniatura");

                entity.Property(e => e.Nombre)
                    .IsRequired()
                    .HasMaxLength(100)
                    .IsUnicode(false)
                    .HasComment("Nombre del evento");
            });

            modelBuilder.Entity<EventoUsuario>(entity =>
            {
                entity.HasKey(e => new { e.IdEvento, e.IdLogin });

                entity.HasComment("Tabla que contiene la informaci??e la Evento y el usuario");

                entity.Property(e => e.IdEvento)
                    .HasColumnName("Id_Evento")
                    .HasComment("Llave For?a");

                entity.Property(e => e.IdLogin)
                    .HasColumnName("Id_Login")
                    .HasComment("Llave For?a");
            });

            modelBuilder.Entity<Localizacion>(entity =>
            {
                entity.HasComment("Tabla que contiene la informaci??e la Localizaci??");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))")
                    .HasComment("Si activo en el sistema");

                entity.Property(e => e.Comentario)
                    .HasMaxLength(100)
                    .IsUnicode(false)
                    .HasComment("Descripcion de la ubicacion o la direcci??");

                entity.Property(e => e.Direccion)
                    .HasMaxLength(100)
                    .IsUnicode(false)
                    .HasComment("Descripcion de la ubicacion o la direcci??");

                entity.Property(e => e.IdEvento)
                    .HasColumnName("Id_Evento")
                    .HasComment("Llave For?a");

                entity.Property(e => e.Latitud)
                    .HasColumnType("decimal(20, 0)")
                    .HasComment("Latitud de la georeferenciaci??e googlemaps");

                entity.Property(e => e.Longitud)
                    .HasColumnType("decimal(20, 0)")
                    .HasComment(@"Longitud de la georeferenciaci??e googlemaps
Longitud de la georeferenciaci??e googlemaps");
            });

            modelBuilder.Entity<Login>(entity =>
            {
                entity.HasComment("Tabla que contiene la informaci??el login asociado a un usuario");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))")
                    .HasComment("Si activo en el sistema");

                entity.Property(e => e.Email)
                    .IsRequired()
                    .HasMaxLength(20)
                    .IsUnicode(false)
                    .HasComment("Email del usuario");

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasMaxLength(8)
                    .IsUnicode(false)
                    .HasComment("Clave del usuario");

                entity.Property(e => e.UserName)
                    .IsRequired()
                    .HasMaxLength(20)
                    .IsUnicode(false)
                    .HasComment("Username de la persona");
            });

            modelBuilder.Entity<ParametrizacionObjetos>(entity =>
            {
                entity.HasComment("Parametrizacion de los objetos");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))")
                    .HasComment("Activo en la lista.");

                entity.Property(e => e.Nombre)
                    .HasMaxLength(50)
                    .IsUnicode(false)
                    .HasComment("nombre de la categoria");

                entity.Property(e => e.Valor)
                    .IsRequired()
                    .HasMaxLength(100)
                    .IsUnicode(false)
                    .HasComment("Valores que va contener la categoria");
            });

            modelBuilder.Entity<Publicaciones>(entity =>
            {
                entity.HasComment("Tabla que contiene la informaci??e las publicaciones asociadas a una Evento");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))");

                entity.Property(e => e.Comentario)
                    .IsRequired()
                    .HasMaxLength(150)
                    .IsUnicode(false)
                    .HasComment("Comentario del post");

                entity.Property(e => e.FechaIngreso)
                    .HasColumnType("datetime")
                    .HasComment("Fecha cuando s registra el post ");

                entity.Property(e => e.IdEvento)
                    .HasColumnName("Id_Evento")
                    .HasComment("Llave Foranea");

                entity.Property(e => e.IdLogin)
                    .HasColumnName("Id_Login")
                    .HasComment("Llave Foranea");

                entity.Property(e => e.Imagen)
                    .HasColumnType("image")
                    .HasComment("Imagen de la publicaci??");
            });

            modelBuilder.Entity<Usuario>(entity =>
            {
                entity.HasComment("Tabla que contiene la informaci??el usuario");

                entity.Property(e => e.Id)
                    .HasComment("Id de la tabla")
                    .ValueGeneratedNever();

                entity.Property(e => e.Activo)
                    .IsRequired()
                    .HasMaxLength(1)
                    .IsUnicode(false)
                    .IsFixedLength()
                    .HasDefaultValueSql("((1))")
                    .HasComment("Si activo en el sistema");

                entity.Property(e => e.Apellidos)
                    .IsRequired()
                    .HasMaxLength(30)
                    .IsUnicode(false)
                    .HasComment("Apellidos  de la persona o usuario");

                entity.Property(e => e.IdLogin)
                    .HasColumnName("Id_Login")
                    .HasComment("Llave For?a");

                entity.Property(e => e.Imagen)
                    .HasColumnType("image")
                    .HasComment("Imagen de la persona o usuario");

                entity.Property(e => e.Nombres)
                    .IsRequired()
                    .HasMaxLength(30)
                    .IsUnicode(false)
                    .HasComment("Nombres de la persona o usuario");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
