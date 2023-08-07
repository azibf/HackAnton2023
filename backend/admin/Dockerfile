FROM python:3.11-slim as base

ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

WORKDIR /src/

RUN apt update && apt install -y \
    build-essential \
    default-libmysqlclient-dev \
    pkg-config

FROM base as builder

RUN pip install poetry

COPY pyproject.toml pyproject.toml

RUN poetry install --no-dev --no-root

RUN mkdir app && touch app/__init__.py

RUN poetry build

FROM base as final

COPY --from=builder /src/dist dist

RUN pip install --no-cache /src/dist/*.whl

COPY app .

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]
