CREATE TABLE [dbo].[Usuario] (
    [Id]        INT          NOT NULL,
    [Nombres]   VARCHAR (30) NOT NULL,
    [Apellidos] VARCHAR (30) NOT NULL,
    [Imagen]    IMAGE        NULL,
    [Id_Login]  INT          NOT NULL,
    [Activo]    CHAR (1)     DEFAULT ((1)) NOT NULL,
    CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED ([Id] ASC)
);


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información del usuario', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Nombres de la persona o usuario', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario', @level2type = N'COLUMN', @level2name = N'Nombres';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Apellidos  de la persona o usuario', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario', @level2type = N'COLUMN', @level2name = N'Apellidos';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Imagen de la persona o usuario', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario', @level2type = N'COLUMN', @level2name = N'Imagen';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foránea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario', @level2type = N'COLUMN', @level2name = N'Id_Login';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Si activo en el sistema', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Usuario', @level2type = N'COLUMN', @level2name = N'Activo';

