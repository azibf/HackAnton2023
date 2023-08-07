from os import getenv
from pathlib import Path
from typing import Dict

from dotenv import load_dotenv

load_dotenv()

BASE_DIR = Path(__file__).resolve().parent.parent

SECRET_KEY = getenv(
    "DJANGO_SECRET_KEY",
    "django-insecure-d6f4*%7!1d^co=4uxhb4utym9)yg5kkv=dl&0+76@&&!)ip=9t",
)

DEBUG = int(getenv("DJANGO_DEBUG", "0"))

ALLOWED_HOSTS = ["*"]

CORS_ALLOW_ALL_ORIGINS = True

CSRF_TRUSTED_ORIGINS = [
    "http://127.0.0.1:8000",
    "http://localhost:8000",
    "https://127.0.0.1:8000",
    "https://localhost:8000",
]

INSTALLED_APPS = [
    "django.contrib.admin",
    "django.contrib.auth",
    "django.contrib.contenttypes",
    "django.contrib.sessions",
    "django.contrib.messages",
    "django.contrib.staticfiles",
    "django_mysql",
    "user",
    "challenge",
    "event",
    "broadcast",
]

MIDDLEWARE = [
    "django.middleware.security.SecurityMiddleware",
    "django.contrib.sessions.middleware.SessionMiddleware",
    "django.middleware.common.CommonMiddleware",
    "django.middleware.csrf.CsrfViewMiddleware",
    "django.contrib.auth.middleware.AuthenticationMiddleware",
    "django.contrib.messages.middleware.MessageMiddleware",
    "django.middleware.clickjacking.XFrameOptionsMiddleware",
]

ROOT_URLCONF = "admin.urls"

STATIC_ROOT = BASE_DIR / "static"

STATIC_URL = "/static/"

TEMPLATES = [
    {
        "BACKEND": "django.template.backends.django.DjangoTemplates",
        "DIRS": [BASE_DIR / "templates"],
        "APP_DIRS": True,
        "OPTIONS": {
            "context_processors": [
                "django.template.context_processors.debug",
                "django.template.context_processors.request",
                "django.contrib.auth.context_processors.auth",
                "django.contrib.messages.context_processors.messages",
            ],
        },
    },
]

WSGI_APPLICATION = "admin.wsgi.application"


def database_config(name: str) -> Dict[str, str]:
    return {
        "NAME": name,
        "ENGINE": "django.db.backends.mysql",
        "USER": "root",
        "PASSWORD": getenv("MYSQL_ROOT_PASSWORD", "password"),
        "PORT": getenv("DB_PORT", "3306"),
        "HOST": getenv("DB_HOST", "localhost"),
        "OPTIONS": {
            "charset": "utf8mb4",
        },
    }


DATABASE_ROUTERS = (
    "user.db_router.DBRouter",
    "challenge.db_router.DBRouter",
    "event.db_router.DBRouter",
    "broadcast.db_router.DBRouter",
)

DATABASES = {
    "default": database_config("DjangoAdmin"),
    "user": database_config("User"),
    "challenge": database_config("Challenge"),
    "event": database_config("Event"),
    "broadcast": database_config("Broadcast"),
}

DEFAULT_AUTO_FIELD = "django.db.models.BigAutoField"

AUTH_PASSWORD_VALIDATORS = [
    {
        "NAME": "django.contrib.auth.password_validation.UserAttributeSimilarityValidator",
    },
    {
        "NAME": "django.contrib.auth.password_validation.MinimumLengthValidator",
    },
    {
        "NAME": "django.contrib.auth.password_validation.CommonPasswordValidator",
    },
    {
        "NAME": "django.contrib.auth.password_validation.NumericPasswordValidator",
    },
]

LANGUAGE_CODE = "en-us"

TIME_ZONE = "Europe/Moscow"

USE_I18N = True

USE_TZ = True
