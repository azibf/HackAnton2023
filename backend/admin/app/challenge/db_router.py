from challenge.models import Award, Challenge, AwardSubmit, ChallengeSubmit


class DBRouter:
    models = (Award, AwardSubmit, Challenge, ChallengeSubmit)

    def db_for_read(self, model, **hints):
        if model in self.models:
            return "challenge"
        return None

    def db_for_write(self, model, **hints):
        if model in self.models:
            return "challenge"
        return None
