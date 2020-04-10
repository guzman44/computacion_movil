CREATE TABLE [dbo].[EventoUsuario] (
    [Id_Evento] INT NOT NULL,
    [Id_Login]  INT NOT NULL,
    CONSTRAINT [PK_EventoUsuario] PRIMARY KEY CLUSTERED ([Id_Evento] ASC, [Id_Login] ASC),
    CONSTRAINT [FK_EventoUsuario_Evento] FOREIGN KEY ([Id_Evento]) REFERENCES [dbo].[Evento] ([Id]),
    CONSTRAINT [FK_EventoUsuario_Login] FOREIGN KEY ([Id_Login]) REFERENCES [dbo].[Login] ([Id])
);




GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información de la Evento y el usuario', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'EventoUsuario';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foránea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'EventoUsuario', @level2type = N'COLUMN', @level2name = N'Id_Evento';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foránea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'EventoUsuario', @level2type = N'COLUMN', @level2name = N'Id_Login';

