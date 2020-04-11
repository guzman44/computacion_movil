CREATE TABLE [dbo].[Categoria] (
    [Id]            INT      IDENTITY (1, 1) NOT NULL,
    [Id_Categoria]  INT      NOT NULL,
    [Id_Login]      INT      NOT NULL,
    [Id_Evento]     INT      NOT NULL,
    [Activo]        CHAR (1) DEFAULT ((1)) NOT NULL,
    [FechaRegistro] DATETIME NOT NULL,
    CONSTRAINT [PK_Categoria] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Categoria_Evento] FOREIGN KEY ([Id_Evento]) REFERENCES [dbo].[Evento] ([Id]),
    CONSTRAINT [FK_Categoria_Login] FOREIGN KEY ([Id_Login]) REFERENCES [dbo].[Login] ([Id])
);




GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información de los Categoria', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foranea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria', @level2type = N'COLUMN', @level2name = N'Id_Categoria';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foranea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria', @level2type = N'COLUMN', @level2name = N'Id_Login';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foranea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria', @level2type = N'COLUMN', @level2name = N'Id_Evento';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Si es eliminado o no (1=activo y 0=Eliminado)', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria', @level2type = N'COLUMN', @level2name = N'Activo';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Fecha de registro del like o Seguir', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Categoria', @level2type = N'COLUMN', @level2name = N'FechaRegistro';

