from fastapi import FastAPI

app = FastAPI()


# Invalidates the cache for a calendar
@app.post("/calendar/invalidate")
def invalidate():
    raise NotImplementedError


@app.get("/event")
def list_events():
    raise NotImplementedError


@app.get("/event/{event_id}")
def get_event():
    raise NotImplementedError


@app.post("/event")
def create_event():
    raise NotImplementedError


@app.post("/event/{event_id}")
def update_event():
    raise NotImplementedError


@app.delete("/event/{event_id}")
def delete_event():
    raise NotImplementedError
