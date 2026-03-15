function ytdlp()
  local cmd = "yt-dlp --js-runtimes node:/home/wayne/.nvm/versions/node/v22.22.1/bin/node --get-url "..vlc.access.."://"..vlc.path
  cmd = cmd.." | head -n 1"
  vlc.msg.dbg( cmd )
  local f = assert(io.popen(cmd, 'r'))
  local s = assert(f:read('*a'))
  f:close()
  if false then return s end
  s = string.gsub(s, '^%s+', '')
  s = string.gsub(s, '%s+$', '')
  s = string.gsub(s, '[\n\r]+', '')
  return s
end

function probe()
    vlc.msg.dbg("dailymotion probing...")
    probe_ret = ( ( vlc.access == "http" or vlc.access == "https" ) and ( string.match( vlc.path, "^www%.dailymotion%.com/" )) )
    vlc.msg.dbg(probe_ret)
    return probe_ret
end

function parse()
    local ret_video_url=ytdlp()
    vlc.msg.dbg(ret_video_url)
    return { { path=ret_video_url, title=vlc.path } }
end
