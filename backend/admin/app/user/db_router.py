from user.models import User, Team, Telegram


class DBRouter:
    models = (User, Team, Telegram)

    def db_for_read(self, model, **hints):
        if model in self.models:
            return "user"
        return None

    def db_for_write(self, model, **hints):
        if model in self.models:
            return "user"
        return None
